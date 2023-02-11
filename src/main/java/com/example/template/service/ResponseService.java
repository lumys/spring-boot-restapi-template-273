package com.example.template.service;

import com.example.template.domain.CommonResult;
import com.example.template.domain.ListResult;
import com.example.template.domain.SingleResult;
import com.example.template.exception.CustomException;
import com.example.template.exception.FailException;
import com.example.template.exception.SuccessException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ResponseService {
    private final SuccessException SUCCESS_EXCEPTION = new SuccessException();
    private final FailException FAIL_EXCEPTION = new FailException();

    // enum 으로 api 요청 결과에 대한 code, message 를 정의합니다.
    private final MessageSource messageSource;

//    // 파일 다운로드 결과를 처리하는 메소드
//    public ResponseEntity<ResourceRegion> getMediaResult(
//        Resource resource,
//        HttpServletRequest request,
//        HttpHeaders headers
//    ) {
//        try {
//            HttpRange httpRange = headers.getRange().stream().findFirst().orElse(null);
//            boolean isPart = false;
//
//            if (httpRange != null) {
//                isPart = true;
//            }
//
//            ResourceRegion region = resourceRegion(resource, httpRange);
//            return ResponseEntity.status(isPart ? HttpStatus.PARTIAL_CONTENT : HttpStatus.OK)
//                .contentType(MediaTypeFactory.getMediaType(resource).orElse(MediaType.APPLICATION_OCTET_STREAM))
//                .header(
//                    HttpHeaders.CONTENT_DISPOSITION,
//                    "attachment; filename=\"" + resource.getFilename() + "\""
//                )
//                .header(HttpHeaders.ACCEPT_RANGES, "bytes")
//                .body(region);
//        } catch (IOException ioe) {
//            throw new FileNotFoundMsgException();
//        }
//    }
//
//    private ResourceRegion resourceRegion(Resource resource, HttpRange httpRange) throws IOException {
//        long contentLength = resource.contentLength();
//
//        if (httpRange != null) {
//            long start = httpRange.getRangeStart(contentLength);
//            long end = httpRange.getRangeEnd(contentLength);
//            long rangeLength = end - start + 1;
//
//        return new ResourceRegion(resource, start, rangeLength);
//        } else {
//            return new ResourceRegion(resource, 0, contentLength);
//        }
//    }

    // 단일 건 결과를 처리하는 메소드
    public <T> SingleResult<T> getSingleResult(T data) {
        SingleResult<T> result = new SingleResult<>();
        result.setData(data);
        setSuccessResult(result);
        return result;
    }

    // 다중 건 결과를 처리하는 메소드
    public <T> ListResult<T> getListResult(List<T> list) {
        return getListResult(list, list.size());
    }

    // 다중 건 결과를 처리하는 메소드
    public <T> ListResult<T> getListResult(List<T> list, int total) {
        ListResult<T> result = new ListResult<>();
        result.setTotal(total);
        result.setList(list);
        setSuccessResult(result);
        return result;
    }

    // 실패 결과만 처리하는 메소드
    public CommonResult getFailResult() {
        CommonResult result = new CommonResult();
        result.setSuccess(false);
        result.setCode(Integer.parseInt(getMessage(FAIL_EXCEPTION.getCode())));
        result.setMessage(getMessage(FAIL_EXCEPTION.getMsg()));
        return result;
    }

    public CommonResult getFailResult(CustomException e) {
        return getFailResult(e, null);
    }

    public CommonResult getFailResult(CustomException e, Object[] args) {
        CommonResult result = new CommonResult();
        result.setSuccess(false);
        result.setCode(Integer.parseInt(getMessage(e.getCode())));
        result.setMessage(getMessage(e.getMsg(), args));
        return result;
    }

    // 성공 결과만 처리하는 메소드
    public CommonResult getSuccessResult() {
        CommonResult result = new CommonResult();
        setSuccessResult(result);
        return result;
    }

    // 결과 모델에 api 요청 성공 데이터를 세팅하는 메소드
    private void setSuccessResult(CommonResult result) {
        result.setSuccess(true);
        result.setCode(Integer.parseInt(getMessage(SUCCESS_EXCEPTION.getCode())));
        result.setMessage(getMessage(SUCCESS_EXCEPTION.getMsg()));
    }

    public CommonResult getSuccessResult(CustomException e) {
        return getSuccessResult(e, null);
    }

    public CommonResult getSuccessResult(CustomException e, Object[] args) {
        CommonResult result = new CommonResult();
        result.setSuccess(true);
        result.setCode(Integer.parseInt(getMessage(e.getCode())));
        result.setMessage(getMessage(e.getMsg(), args));
        return result;
    }

    // code 정보에 해당하는 메시지를 조회합니다.
    private String getMessage(String code) {
        return getMessage(code, null);
    }
    // code 정보, 추가 argument 로 현재 locale 에 맞는 메시지를 조회합니다.
    private String getMessage(String code, @Nullable Object[] args) {
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }
}
