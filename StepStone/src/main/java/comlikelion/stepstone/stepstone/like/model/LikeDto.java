package comlikelion.stepstone.stepstone.like.model;

import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class LikeDto {

    /**
     * 데이터 전달 모델 클래스
     * Data Transfer Object
     */

    private Long likeId;

    @Setter
    private UUID userId;

    @Setter
    private UUID postId;

    @Setter
    private LocalDateTime createdAt;

    public static LikeDto toEntity(LikeEntity entity) {
        LikeDto dto = LikeDto.builder()
                .likeId(entity.getLikeId())
                .userId(entity.getUserId())
                .postId(entity.getPostId())
                .createdAt(entity.getCreatedAt())
                .build();

        return dto;
    }
}
