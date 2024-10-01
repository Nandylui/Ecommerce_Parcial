package com.ecommerce.controlador;

import java.nio.file.Files;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.nio.file.StandardCopyOption;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
/*import org.springframework.data.repository.query.Param;
*/import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.dto.ProductoDTO;
import com.ecommerce.modelo.Producto;
import com.ecommerce.repositorio.ProductoRepositorio;

@Controller
@RequestMapping("/productos")
public class ProductoControlador {

	@Autowired
	private ProductoRepositorio servicio;

	@GetMapping({ "", "/" })
	public String showProductosList(Model model) {
		List<Producto> productos = servicio.findAll(Sort.by(Sort.Direction.DESC, "id"));
		model.addAttribute("producto", productos);
		return "productos/index";
	}

	@GetMapping("/crear")
	public String showCreatePage(Model model) {
		ProductoDTO productoDTO = new ProductoDTO();
		model.addAttribute("productoDTO", productoDTO);
		return "productos/crear";
	}

	@PostMapping("/crear")
	public String createProduct(@Valid @ModelAttribute ProductoDTO productoDTO, BindingResult result) {

		if (productoDTO.getImagenNombre().isEmpty()) {
			result.addError(new FieldError("productoDTO", "imagenNombre", "the image file is required"));
		}

		if (result.hasErrors()) {
			return "productos/crear";
		}

		// salvar imagen file
		MultipartFile image = productoDTO.getImagenNombre();
		String storageFileName = image.getOriginalFilename();
		try {
			String uploadDir = "public/imagenesR/";
			Path uploadPath = Paths.get(uploadDir);

			if (!Files.exists(uploadPath)) {
				Files.createDirectories(uploadPath);
			}

			try (InputStream inputStream = image.getInputStream()) {
				Files.copy(inputStream, Paths.get(uploadDir + storageFileName), StandardCopyOption.REPLACE_EXISTING);
			}
		} catch (Exception ex) {
			System.out.println("Exception:" + ex.getMessage());
		}

		Producto producto = new Producto();
		producto.setNombre(productoDTO.getNombre());
		producto.setMarca(productoDTO.getMarca());
		producto.setCategoria(productoDTO.getCategoria());
		producto.setPrecio(productoDTO.getPrecio());
		producto.setDescripcion(productoDTO.getDescripcion());
		producto.setColor(productoDTO.getColor());
		producto.setImagenNombre(storageFileName);

		servicio.save(producto);

		return "redirect:/productos";
	}

	// --------------------------------
	@GetMapping("/editar")
	public String ShowEditPage(Model model, @RequestParam Long id) {

		try {
			Producto producto = servicio.findById(id).get();
			model.addAttribute("producto", producto);

			ProductoDTO productoDTO = new ProductoDTO();
			productoDTO.setNombre(producto.getNombre());
			productoDTO.setMarca(producto.getMarca());
			productoDTO.setCategoria(producto.getCategoria());
			productoDTO.setPrecio(producto.getPrecio());
			productoDTO.setDescripcion(producto.getDescripcion());
			productoDTO.setColor(producto.getColor());

			model.addAttribute("productoDTO", productoDTO);

		} catch (Exception ex) {
			System.out.println("Exception:" + ex.getMessage());
			return "redirect:/productos";
		}

		return "productos/editar";
	}
	
	@PostMapping("/editar/{id}")
	public String editarProducto(Model model, @PathVariable Long id, @Valid @ModelAttribute ProductoDTO productoDTO, BindingResult result) {
	    Producto producto = servicio.findById(id).orElse(null);
	    if (producto == null) {
	        return "redirect:/productos";
	    }

	    model.addAttribute("producto", producto);

	    if (result.hasErrors()) {
	        return "productos/editar";  // Retornar al formulario si hay errores
	    }

	    // Manejo de la imagen
	    if (!productoDTO.getImagenNombre().isEmpty()) {
	        String uploadDir = "public/imagenesR/";
	        Path oldImagePath = Paths.get(uploadDir + producto.getImagenNombre());

	        try {
	            Files.delete(oldImagePath); // Eliminar imagen anterior
	        } catch (Exception ex) {
	            System.out.println("Exception:" + ex.getMessage());
	        }

	        // Guardar nueva imagen
	        MultipartFile image = productoDTO.getImagenNombre();                
	        String storageFileName = image.getOriginalFilename();

	        try (InputStream inputStream = image.getInputStream()) {
	            Files.copy(inputStream, Paths.get(uploadDir + storageFileName), StandardCopyOption.REPLACE_EXISTING);
	        } catch (IOException ex) {
	            result.addError(new FieldError("productoDTO", "imagenNombre", "Error al cargar la imagen."));
	            return "productos/editar"; // Retornar al formulario si falla la carga de imagen
	        }

	        producto.setImagenNombre(storageFileName);
	    }

	    // Actualizar los campos del producto
	    producto.setNombre(productoDTO.getNombre());
	    producto.setMarca(productoDTO.getMarca());
	    producto.setCategoria(productoDTO.getCategoria());
	    producto.setPrecio(productoDTO.getPrecio());
	    producto.setDescripcion(productoDTO.getDescripcion());
	    producto.setColor(productoDTO.getColor());

	    try {
	        servicio.save(producto); // Guardar cambios
	    } catch (Exception ex) {
	        result.addError(new FieldError("productoDTO", "nombre", "Error al guardar el producto."));
	        return "productos/editar"; // Retornar al formulario si hay errores al guardar
	    }

	    return "redirect:/productos"; // Redirigir a la lista de productos después de guardar
	}


	
	// --------------------------------
	@GetMapping("/eliminar")
	public String eliminarProducto(@RequestParam Long id) {

		try {
			Producto producto = servicio.findById(id).get();

			// delete product image
			Path imagePath = Paths.get("public/imagenesR" + producto.getImagenNombre());

			try {
				Files.delete(imagePath);
			} catch (Exception ex) {
				System.out.println("Exception:" + ex.getMessage());
			}

			// delete the product
			servicio.delete(producto);

		}

		catch (Exception ex) {
			System.out.println("Exception:" + ex.getMessage());
		}

		return "redirect:/productos";

	}

}
