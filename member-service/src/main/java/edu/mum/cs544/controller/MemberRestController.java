package edu.mum.cs544.controller;

import edu.mum.cs544.domain.Member;
import edu.mum.cs544.service.IMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/member")
public class MemberRestController {

    @Autowired
    private IMemberService memberService;

    @GetMapping
    public List<Member> getAll() {
        return memberService.getAll();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> get(@PathVariable int id) throws RuntimeException {

        try {
            return new ResponseEntity<>(memberService.get(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public RedirectView add(@Valid @RequestBody Member member) {

        memberService.add(member);
        return new RedirectView("/member/" + member.getId());
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> update(@PathVariable int id, @Valid @RequestBody Member member)
            throws RuntimeException {

        if (id != member.getId())
            return new ResponseEntity<>("Illegal request", HttpStatus.BAD_REQUEST);
        try {
            memberService.update(member);
            return new ResponseEntity<>(member, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) throws RuntimeException {

        try {
            memberService.delete(id);
            return new ResponseEntity<>("Member has been deleted.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

        return errors;
    }

}
