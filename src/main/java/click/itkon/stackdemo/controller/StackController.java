package click.itkon.stackdemo.controller;

import click.itkon.stackdemo.dto.DataDto;
import click.itkon.stackdemo.service.DataService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StackController {

    private final DataService dataService;

    @PostMapping("push")
    public ResponseEntity<DataDto> push(@Valid @RequestBody DataDto dataDto) {
        return ResponseEntity.ok(dataService.push(dataDto));
    }

    @GetMapping("pop")
    public ResponseEntity<DataDto> pop() {
        return ResponseEntity.ok(dataService.getLastAndRemove());
    }
}
