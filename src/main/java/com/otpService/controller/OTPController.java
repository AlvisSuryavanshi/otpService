package com.otpService.controller;

import com.otpService.payload.OTPRequest;
import com.otpService.payload.OTPVerification;
import com.otpService.service.OTPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OTPController {

    @Autowired
    private OTPService otpService;

    @PostMapping("/send-otp")
    public ResponseEntity<String> sendOtp(@RequestBody OTPRequest  otpRequest) {

        otpService.sendOtp(otpRequest.getPhoneNumber());
        return new ResponseEntity<>("Otp has been send",HttpStatus.OK);
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<String> verifyOtp(@RequestBody OTPVerification otpVerification) {
        String s = otpService.verifyOtp(otpVerification.getPhoneNumber(), otpVerification.getEnteredOtp());
        return new ResponseEntity<>(s,HttpStatus.OK);
    }
}
