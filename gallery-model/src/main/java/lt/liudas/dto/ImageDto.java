package lt.liudas.dto;

import lombok.Data;

import java.util.List;

@Data
public class ImageDto {
    String title;
    String description;
    List<CategoryDto> categories;
    List<TagDto> tags;
}
