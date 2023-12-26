package com.file.upload.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.file.upload.controller.entity.Product;
import com.file.upload.service.ProductService;

@Controller
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping("/upload")
	public String showUploadForm(Model model) {
		model.addAttribute("product", new Product());

		return "upload";
	}

	@PostMapping("/fileUpload")
	public String handleFileUpload(@ModelAttribute Product product, @RequestParam("file") MultipartFile file,
			Model model) {
		if (file.isEmpty()) {
			model.addAttribute("errorMessage", "Please select a file to upload.");
		}

		try {

			byte[] fileByte = file.getBytes();

			if (productIsValid(product)) {
				productService.saveFileData(product, fileByte);
			} else {
				model.addAttribute("errorMessage", "Invalid product data.");
			}

		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMessage", "Error processing the uploaded file.");
			return "redirect:/upload";

		}

		return "redirect:/upload";
	}

	private boolean productIsValid(Product product) {
		if (product == null) {

			return false;
		}

		if (product.getProductName() == null || product.getProductPrice() <= 0 || product.getProductQuantity() < 0) {
			return false;
		}

		if (product.getProductName().length() < 3 || product.getProductName().length() > 50) {

			return false;

		}

		return true;

	}

}
