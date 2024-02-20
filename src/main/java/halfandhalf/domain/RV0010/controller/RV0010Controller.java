package halfandhalf.domain.RV0010.controller;


import halfandhalf.com.exception.FileUploadException;
import halfandhalf.domain.LG0010.oauth.jwt.AuthTokensGenerator;
import halfandhalf.domain.LG0010.oauth.jwt.JwtTokenProvider;
import halfandhalf.domain.RV0010.dto.RV0010Dto;
import halfandhalf.domain.RV0010.dto.RV0011Dto;
import halfandhalf.domain.RV0010.service.RV0010Service;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin(origins = {"http://www.utteok.com"}, allowCredentials = "true")
@RestController
@RequestMapping("/api/rv")
@RequiredArgsConstructor
public class RV0010Controller {

    private final RV0010Service rV0010Service;
    private final JwtTokenProvider jwtProvider;
    private final AuthTokensGenerator authTokensGenerator;

    /*
     *  나도 추천할래 가져오기 to MainPage
     */
    @GetMapping(value="/ViewOneFromRecommend", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> ViewToOne(@RequestBody RV0010Dto rv0010Dto) {
        try {
            RV0010Dto recommend = rV0010Service.findOneFromRecommend(rv0010Dto);
            return ResponseEntity.ok(recommend);
        }
        catch(Exception e){
            // 그 외 에러의 경우 500 메세지
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 내부 오류");
        }
    }

    /*
     *  나도 추천할래
     */
    @PostMapping("/getRecommendToPage")
    public ResponseEntity<?> getRecommendToPage(@RequestBody RV0011Dto rv0011Dto, HttpServletRequest request) {
//        https://epozen-dt.github.io/SpringBoot-pagination/
        try {
            String accessToken = jwtProvider.getAccessToken(request);
            Long user_id = 0L;  // main에서 4개만 가져올 때 사용
            if(StringUtils.hasText(accessToken)) {
                user_id = authTokensGenerator.extractMemberId(accessToken);
            }
            List<RV0010Dto> recommend = rV0010Service.findRecommendByPage(rv0011Dto, user_id);
            return ResponseEntity.ok(recommend);
        }
        catch(Exception e){
            // 그 외 에러의 경우 500 메세지
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 내부 오류");
        }
    }

    /*
     *  나도 추천할래 등록하기
     */
    @PostMapping(value="/saveRecommend", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveRecommend(@ModelAttribute RV0010Dto rv0010Dto,
                                           @RequestPart(value = "file", required=false) MultipartFile file,
                                           HttpServletRequest request) {
        try {
            String accessToken = jwtProvider.getAccessToken(request);
            Long user_id = authTokensGenerator.extractMemberId(accessToken);
            rv0010Dto.setUser_id(user_id);

            rV0010Service.saveRecommend(rv0010Dto, file);
            return ResponseEntity.ok("SUCCESS");
        } catch (FileUploadException e) {
            // 파일 업로드 실패한 경우 에러 메세지 + 400 상태 코드 반환
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("파일 업로드 오류");
        }
        catch (DataIntegrityViolationException e) {
            // 인증 실패한 경우 에러 메세지 + 400 상태 코드 반환
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("내용을 입력해 주세요");
        }
        catch(Exception e){
            // 그 외 에러의 경우 500 메세지
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 내부 오류");
        }
    }
}
