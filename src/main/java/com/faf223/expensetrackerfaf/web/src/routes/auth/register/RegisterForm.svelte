<script>
    // eslint-disable-next-line no-unused-vars
    import * as EmailValidator from 'email-validator';
    import {onMount} from "svelte";
    import {getCookie, setCookie} from "svelte-cookie";
    import axios from "axios";

    let isErrorVisible = false;

    // eslint-disable-next-line no-unused-vars
    let username, email, password, name, surname;
    let message = ""

    onMount(async () => {

        const access_token = getCookie('access_token');
        const refresh_token = getCookie('refresh_token');

        if (access_token && refresh_token) {
            window.location.href = '/dashboard';
        }

    });

    async function submitForm(event) {
        event.preventDefault();

        try {
            const data = {
                firstname: name,
                lastname: surname,
                username: username,
                email: email,
                password: password,
            };

            console.log(data)

            const response = await axios.post('https://trackio.online:8081/api/v1/auth/register', data);

            const { access_token, refresh_token } = response.data;

            setCookie('access_token', access_token);
            setCookie('refresh_token', refresh_token);

            window.location.href = '/dashboard'
        } catch (error) {
            console.error('Login failed:', error);
        }
    }

    // function submitForm(event) {
    //     event.preventDefault();
    //     // console.log("Tried to submit!");
    //     // console.log("Valid? ", (validateEmail() && validateUsername() && validatePassword() ? "Yes" : "No"));
    // }

    // function validateEmail() {
    //     let valid = EmailValidator.validate(username);
    //     isErrorVisible = valid ? false : true;
    //     message = isErrorVisible ? "Invalid e-mail!" : "";
    //     return valid;
    // }
    //
    // function validatePassword() {
    //     let valid = password.value != '';
    //     isErrorVisible = valid ? false : true;
    //     message = isErrorVisible ? "Invalid password!" : "";
    //     return valid;
    // }
    //
    // function validateUsername() {
    //     let valid = username.value != '';
    //     isErrorVisible = valid ? false : true;
    //     message = isErrorVisible ? "Invalid password!" : "";
    //     return valid;
    // }

</script>

<svelte:head>
    <link rel="icon" type="image/x-icon" href="../favicon.png" />
    <title>Register into Track.IO</title>
</svelte:head>


<div class="animated bounceInDown">
    <div class="container">
        {#if isErrorVisible}
            <span class="error animated tada" id="msg">{message}</span>
        {/if}
        <form name="registerForm" class="registerForm" on:submit={submitForm}>
            <h1 id="formTitle">Track<span>.io</span></h1>
            <h5>Sign up for a new account.</h5>
            <input id="usernameInput" type="text" name="username" placeholder="Username" autocomplete="off" on:input={
                event => {username = event.target.value}
            }>
            <input id="nameInput" type="text" name="name" placeholder="Name" autocomplete="off" on:input={
                event => {name = event.target.value}
            }>
            <input id="surnameInput" type="text" name="surname" placeholder="Surname" autocomplete="off" on:input={
                event => {surname = event.target.value}
            }>
            <input id="emailInput" type="text" name="email" placeholder="Email" autocomplete="off" on:input={
                event => {email = event.target.value}
            }>
            <input id="passwordInput" type="password" name="password" placeholder="Password" autocomplete="off" on:input={
                event => {password = event.target.value}
            }>
            <a href="/auth/recovery" class="recoveryPass">Forgot your password?</a>
            <input type="submit" value="Sign up" class="submitButton">
        </form>
        <a href="/auth/login" class="noAccount">Already have an account? Sign in</a>
    </div>
</div>


<style>
    @import url('https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400');

    .container {
        margin: 0;
        top: 50px;
        left: 50%;
        font-family: 'Source Sans Pro', sans-serif;
        position: absolute;
        text-align: center;
        transform: translateX(-50%);
        background-color: rgb(33, 41, 66);
        border-radius: 9px;
        border-top: 10px solid #79a6fe;
        border-bottom: 10px solid #8BD17C;
        width: 400px;
        height: 750px;
        box-shadow: 1px 1px 108.8px 19.2px rgb(25, 31, 53);
    }

    #formTitle {
        font-family: 'Source Sans Pro', sans-serif;
        color: #5c6bc0;
        margin-top: 94px;
    }

    #formTitle span {
        color: #dfdeee;
        font-weight: lighter;
    }

    .registerForm h5 {
        font-family: 'Source Sans Pro', sans-serif;
        font-size: 13px;
        color: #a1a4ad;
        letter-spacing: 1.5px;
        margin-top: -15px;
        margin-bottom: 70px;
    }

    .registerForm input[type="text"],
    .registerForm input[type="password"] {
        display: block;
        margin: 20px auto;
        background: #262e49;
        border: 0;
        border-radius: 5px;
        padding: 14px 10px;
        width: 320px;
        outline: none;
        color: #d6d6d6;
        -webkit-transition: all .2s ease-out;
        -moz-transition: all .2s ease-out;
        -ms-transition: all .2s ease-out;
        -o-transition: all .2s ease-out;
        transition: all .2s ease-out;
    }

    .registerForm input[type="text"]:focus,
    .registerForm input[type="password"]:focus {
        border: 1px solid #79A6FE;
    }

    a {
        color: #5c7fda;
        text-decoration: none;
    }

    a:hover {
        text-decoration: underline;
    }

    .submitButton {
        border: 0;
        background: #7f5feb;
        color: #dfdeee;
        border-radius: 100px;
        width: 340px;
        height: 49px;
        font-size: 16px;
        position: absolute;
        top: 79%;
        left: 8%;
        transition: 0.3s;
        cursor: pointer;
    }

    .submitButton:hover {
        background: #5d33e6;
    }

    .recoveryPass {
        position: relative;
        float: right;
        right: 28px;
    }

    .noAccount {
        position: absolute;
        top: 92%;
        left: 24%;
    }

    .error {
        text-align: center;
        width: 337px;
        height: 20px;
        padding: 2px;
        border: 0;
        border-radius: 5px;
        margin: 10px auto 10px;
        position: absolute;
        top: 31%;
        left: 7.2%;
        color: rgb(190, 67, 29);
    }
</style>