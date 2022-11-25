package enca.eli.springbootmongodb.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "todos")
public class TodoDTO {
    // Data Transfer Object
    // @NotNull for ValidationConfig.java

    @Id
    private String id;

    @NotNull(message = "todo cannot be null")
    private String todo;

    @NotNull(message = "description cannot be null")
    private String description;

    @NotNull(message = "completed cannot be null")
    private Boolean completed;

    private Date createdAt;

    private Date updatedAt;

//    @Override
//    public String toString() {
//        return super.toString();
//    }
}
