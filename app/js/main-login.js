"use strict";

import {loginURL} from "./config.js"

function mainLogin() {
    $("#signing").click(e => {
        console.log("hello");
        $.post(
            loginURL,
            {
                "login": $("#login").val(),
                "password": $("#password").val()
            },
            response => {
                console.log(response);
            }
        )
    });
}

mainLogin();