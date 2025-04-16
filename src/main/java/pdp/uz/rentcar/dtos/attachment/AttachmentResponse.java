package pdp.uz.rentcar.dtos.attachment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pdp.uz.rentcar.entity.CarAttachmentContent;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AttachmentResponse {
    private UUID id;
    private String filename;
    private String fileType;
    private long fileSize;
    private List<CarAttachmentContent> contents;
}
