package com.epam.lab.consts;

import com.epam.lab.models.Message;
import com.epam.lab.models.User;


public interface Constants {
    User DEFAULT_USER = new User("Test", "Test", "selenium.test.v.lohvyniuk@gmail.com", "wylsselenium?");
    Message WHATS_UP_MESSAGE = new Message("volodymyr.lohvyniuk@gmail.com", "cc_cc@gmail.com", "bcc_bcc@gmail.com",
            "Hey, whats up?", "Lorem ipsum dolor sit amet, consectetur adipiscing elit,\n" +
            " sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.\n" +
            " Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.\n" +
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit,\n" +
            "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.\n" +
            "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.");
    Message EPAM_SERVICE_MESSAGE = new Message("volodymyr.lohvyniuk@gmail.com", "cc_cc@gmail.com", "bcc_bcc@gmail.com",
            "EPAM Please check your mailbox", "Hello, I`m writing you to test WebDriver");
}
