package pdp.uz.rentcar.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pdp.uz.rentcar.dtos.attachment.AttachmentRequest;
import pdp.uz.rentcar.dtos.attachment.AttachmentResponse;
import pdp.uz.rentcar.entity.CarAttachment;
import pdp.uz.rentcar.entity.CarAttachmentContent;
import pdp.uz.rentcar.exception.RecordNotFoundException;
import pdp.uz.rentcar.repository.AttachmentContentRepository;
import pdp.uz.rentcar.repository.AttachmentRepository;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AttachmentService {
    private final AttachmentRepository attachmentRepository;
    private final AttachmentContentRepository attachmentContentRepository;

    public AttachmentResponse create(MultipartFile file) throws IOException {
        if (file == null) {
             throw new RecordNotFoundException("File not found");
        }
        CarAttachmentContent attachmentContent = new CarAttachmentContent();
        attachmentContent.setContent(file.getBytes());
        attachmentContentRepository.save(attachmentContent);
        CarAttachment attachment = new CarAttachment();
        attachment.setContent(attachmentContent);
        attachment.setFilename(file.getOriginalFilename());
        attachment.setFileType(file.getContentType());
        attachment.setContent(attachmentContent);
        attachment.setFileSize(file.getSize());
        CarAttachment save = attachmentRepository.save(attachment);

        return AttachmentResponse.builder()
                .id(save.getId())
                .filename(save.getFilename())
                .fileType(save.getFileType())
                .fileSize(save.getFileSize())
                .contents(List.of(save.getContent()))
                .build();
    }
}
