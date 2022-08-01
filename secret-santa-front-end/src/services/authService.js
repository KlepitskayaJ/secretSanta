import axios from "axios";
const API_URL = "http://localhost:8081/";

const register = (email, password, firstname, lastname, phone, address, postcode) => {
    return axios.post(API_URL + "register", {
        email: email,
        password: password,
        first_name: firstname,
        last_name: lastname,
        phone_number: phone,
        address: address,
        postcode: postcode
    });
};
const login = (email, password) => {
    console.log(email);
    console.log(password);
    return axios
        .post(API_URL + "login", {
            username: email,
            password: password
        })
        .then((response) => {
            if (response.data) {
                localStorage.setItem("account", JSON.stringify(response.data));
            }
            return response.data;
        });
};
const logout = () => {
    localStorage.removeItem("account");
};

export default {
    register,
    login,
    logout,
};