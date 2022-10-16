package kr.project.shortlink.api.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.project.shortlink.api.application.ShortLinkService;
import kr.project.shortlink.api.dto.ShortLinkRequest;
import kr.project.shortlink.api.dto.ShortLinkResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Tag(name = "단축링크")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/short-links")
public class ShortLinkController {

    private final ShortLinkService shortLinkService;

    @Operation(summary = "단축링크 생성")
    @PostMapping
    public ResponseEntity<ShortLinkResponse> create(@RequestBody @Validated ShortLinkRequest shortLinkRequest) {
        final ShortLinkResponse shortLinkResponse = shortLinkService.create(shortLinkRequest);

        return ResponseEntity
                .created(linkTo(methodOn(ShortLinkController.class).findById(shortLinkResponse.getShortId())).toUri())
                .body(shortLinkResponse);
    }

    @Operation(summary = "단축링크 조회")
    @GetMapping("/{shortId}")
    public EntityModel<ShortLinkResponse> findById(@PathVariable String shortId) {

        return EntityModel.of(shortLinkService.findById(shortId),
                    linkTo(methodOn(RedirectController.class).redirectByShortId(shortId)).withRel("redirect")
               );
    }
}