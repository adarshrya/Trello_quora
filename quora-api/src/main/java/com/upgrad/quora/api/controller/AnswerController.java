package com.upgrad.quora.api.controller;

import com.upgrad.quora.api.model.AnswerDeleteResponse;
import com.upgrad.quora.api.model.AnswerDetailsResponse;
import com.upgrad.quora.api.model.AnswerEditRequest;
import com.upgrad.quora.api.model.AnswerEditResponse;
import com.upgrad.quora.api.model.AnswerRequest;
import com.upgrad.quora.api.model.AnswerResponse;
import com.upgrad.quora.service.business.AnswerBusinessService;
import com.upgrad.quora.service.entity.AnswerEntity;
import com.upgrad.quora.service.exception.AnswerNotFoundException;
import com.upgrad.quora.service.exception.AuthorizationFailedException;
import com.upgrad.quora.service.exception.InvalidQuestionException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class AnswerController {

  @Autowired
  private AnswerBusinessService answerBusinessService;

  @RequestMapping(method = RequestMethod.POST, path = "/question/{questionId}/answer/create", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<AnswerResponse> createAnswer(
      @RequestHeader("authorization") final String authorization,
      @PathVariable("questionId") final String questionId, final AnswerRequest answerRequest)
      throws AuthorizationFailedException, InvalidQuestionException {
    AnswerResponse answerResponse = new AnswerResponse();
    AnswerEntity answerEntity = answerBusinessService
        .createAnswer(authorization, questionId, answerRequest.getAnswer());
    answerResponse.setId(answerEntity.getUuid());
    answerResponse.setStatus("ANSWER CREATED");
    return new ResponseEntity<AnswerResponse>(answerResponse, HttpStatus.CREATED);
  }

  @RequestMapping(method = RequestMethod.PUT, path = "/answer/edit/{answerId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<AnswerEditResponse> editAnswerContent(
      @RequestHeader("authorization") final String authorization,
      @PathVariable("answerId") final String answerId, final AnswerEditRequest answerEditRequest)
      throws AuthorizationFailedException, AnswerNotFoundException {
    AnswerEditResponse answerEditResponse = new AnswerEditResponse();
    AnswerEntity answerEntity = answerBusinessService
        .editAnswerContent(authorization, answerId, answerEditRequest.getContent());
    answerEditResponse.setId(answerEntity.getUuid());
    answerEditResponse.setStatus("ANSWER EDITED");
    return new ResponseEntity<AnswerEditResponse>(answerEditResponse, HttpStatus.OK);
  }

  @RequestMapping(method = RequestMethod.DELETE, path = "/answer/delete/{answerId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<AnswerDeleteResponse> deleteAnswer(
      @RequestHeader("authorization") final String authorization,
      @PathVariable("answerId") final String answerId)
      throws AuthorizationFailedException, AnswerNotFoundException {
    AnswerDeleteResponse answerDeleteResponse = new AnswerDeleteResponse();
    AnswerEntity answerEntity = answerBusinessService.deleteAnswer(authorization, answerId);
    answerDeleteResponse.setId(answerEntity.getUuid());
    answerDeleteResponse.setStatus("ANSWER DELETED");
    return new ResponseEntity<AnswerDeleteResponse>(answerDeleteResponse, HttpStatus.OK);
  }

  @RequestMapping(method = RequestMethod.GET, path = "/answer/all/{questionId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<List<AnswerDetailsResponse>> getAllAnswersToQuestion(
      @RequestHeader("authorization") final String authorization,
      @PathVariable("questionId") final String questionId)
      throws AuthorizationFailedException, InvalidQuestionException {
    List<AnswerDetailsResponse> answerDetailsResponse = new ArrayList<AnswerDetailsResponse>();
    List<AnswerEntity> answerEntityList = answerBusinessService
        .getAllAnswersToQuestion(authorization, questionId);
    for (AnswerEntity answerEntity : answerEntityList) {
      answerDetailsResponse.add(new AnswerDetailsResponse().id(answerEntity.getUuid())
          .answerContent(answerEntity.getAns())
          .questionContent(answerEntity.getQuestion().getContent()));
    }
    return new ResponseEntity<List<AnswerDetailsResponse>>(answerDetailsResponse, HttpStatus.OK);
  }
}
