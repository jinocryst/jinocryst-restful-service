package net.jinocryst.jinocrystrestfulservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.jinocryst.jinocrystrestfulservice.bean.User;
import net.jinocryst.jinocrystrestfulservice.dao.UserDaoService;
import net.jinocryst.jinocrystrestfulservice.exception.UserNotFoundException;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Objects;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequiredArgsConstructor
@Tag(name = "user-controller", description = "일반 사용자 서비스를 위한 컨트롤러")
public class UserController {

    //final 키워드 + Lombok 을 이용한 생성자에 의한 주입
    private final UserDaoService service;

    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return service.findAll();
    }

//    @GetMapping("/users/{id}")
//    public User retrieveUser(@PathVariable int id) {
//        User user = service.findOne(id);
//
//        if (user == null) {
//            throw new UserNotFoundException(String.format("ID[%s] not found", id));
//        }
//
//        return user;
//    }

    @Operation(summary = "사용자 정보 조회 API", description = "사용자 아이디로 사용자 정보를 조회")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "OK"),
        @ApiResponse(responseCode = "400", description = "BAD_REQUEST"),
        @ApiResponse(responseCode = "404", description = "USER_NOT_FOUND"),
        @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR"),
    })
    @GetMapping("/users/{id}")
    public EntityModel<User> retrieveUser(
            @Parameter(description = "사용자ID", required = true, example = "1")
            @PathVariable int id) {
        User user = service.findOne(id);

        if (user == null) {
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }

        //HATEOAS 활용
        EntityModel<User> entityModel = EntityModel.of(user);

        WebMvcLinkBuilder linTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        entityModel.add(linTo.withRel("all-users"));    // all-users -> http://localhost:8080/users

        /*
        {
            "id": 2,
            "name": "jino2",
            "joinDate": "2024-01-14T11:19:10.967+00:00",
            "_links": {
                "all-users": {
                    "href": "http://localhost:8080/users"
                }
            }
        }
         */
        return entityModel;
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User saveUser = service.save(user);
        System.out.println("saveUser : " + saveUser);

        //[TIP] ServletUriComponentsBuilder 를 이용한 URI 를 헤더에 전달
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saveUser.getId())
                .toUri();

        //[TIP] ResponseEntity 반환 + 201
        return ResponseEntity.created(location).build();
    }


    @DeleteMapping("/users/{id}")
    public ResponseEntity<Objects> deleteUser(@PathVariable int id) {
        User deletedUser = service.deleteById(id);

        if (deletedUser == null) {
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }

        // [TIP] 삭제되면 204 No Content 상태값 변경
        return ResponseEntity.noContent().build();

    }


}
