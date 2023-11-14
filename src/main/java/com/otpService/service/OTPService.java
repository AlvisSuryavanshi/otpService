package com.otpService.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class OTPService {

    @Value("${twilio.account.sid}")
    private String twilioAccountSid;

    @Value("${twilio.auth.token}")
    private String twilioAuthToken;

    @Value("${twilio.phone.number}")
    private String twilioPhoneNumber;

    private final Random random = new Random();

    // Store generated OTPs in memory (Replace with a database in production)


    public void sendOtp(String recipientPhoneNumber) {
        Twilio.init(twilioAccountSid, twilioAuthToken);

        // Generate a random 6-digit OTP
        String otp = generateRandomOtp();

        // Send OTP via Twilio
        Message message = Message.creator(
                new PhoneNumber(recipientPhoneNumber),
                new PhoneNumber(twilioPhoneNumber),
                "Your OTP is: " + otp
        ).create();

        // Store the generated OTP in memory

    }

    private String generateRandomOtp() {
        // Generate a random 6-digit OTP
        int otp = 100_000 + random.nextInt(900_000); // Generates a number between 100,000 and 999,999
        return String.valueOf(otp);
    }
}

