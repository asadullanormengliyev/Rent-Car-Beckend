package pdp.uz.rentcar.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pdp.uz.rentcar.dtos.attachment.AttachmentRequest;
import pdp.uz.rentcar.dtos.attachment.AttachmentResponse;
import pdp.uz.rentcar.service.AttachmentService;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/attachment")
public class AttachmentController {
    private final AttachmentService attachmentService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public AttachmentResponse create(@RequestParam("file") MultipartFile file) throws IOException {
        return attachmentService.create(file);
    }

}
