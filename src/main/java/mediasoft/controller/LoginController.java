package mediasoft.controller;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import mediasoft.dto.worker.WorkerCreateDto;
import mediasoft.dto.worker.WorkerDto;
import mediasoft.service.WorkerService;
import mediasoft.service.authentication.JpaWorkerDetailsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/authentication")
public class LoginController {

    private final JpaWorkerDetailsService jpaWorkerDetailsService;

    @PostMapping("/registration")
       public ResponseEntity<WorkerCreateDto> registration(WorkerCreateDto workerCreateDto) {
        jpaWorkerDetailsService.saveWorker(workerCreateDto);
        return ResponseEntity.ok(workerCreateDto);
    }

//    @PostMapping("/authorize")
//    public ResponseEntity<WorkerDto> auth(WorkerDto workerDto) {
//        String token = workerService.authorize(userRequest);
//
//        AuthResponse authResponse = new AuthResponse(token);
//
//        return ResponseEntity.ok(authResponse);
//    }

}