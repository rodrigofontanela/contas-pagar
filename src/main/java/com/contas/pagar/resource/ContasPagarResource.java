package com.contas.pagar.resource;

import com.contas.pagar.model.ContasPagar;
import com.contas.pagar.repository.ContasPagarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contas-pagar")
public class ContasPagarResource {

    @Autowired
    private ContasPagarRepository contasPagarRepository;

    @GetMapping
    public Page<ContasPagar> find(@RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                  @RequestParam(value = "size", required = false, defaultValue = "50") int size) {
        return contasPagarRepository.findAll(PageRequest.of(page, size));
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ContasPagar find(@PathVariable Long id) {
        return contasPagarRepository.findById(id).get();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ContasPagar create(@RequestBody ContasPagar contasPagar) {
        return contasPagarRepository.save(contasPagar);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ContasPagar update(@RequestBody ContasPagar contasPagar) {
        return contasPagarRepository.save(contasPagar);
    }
}
