package school.sptech.iara.rest.viacep;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "viacep", url = "https://viacep.com.br/ws/")
public interface ViacepClient {
    @GetMapping("{cep}/json")
    ViacepResponse getAddress(@PathVariable String cep);
}
