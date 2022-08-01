import {LOGIN_FAIL, LOGIN_SUCCESS, LOGOUT, REGISTER_FAIL, REGISTER_SUCCESS} from "../actions/type";

const account = JSON.parse(localStorage.getItem("account"));
const initialState = account
    ? { isLoggedIn: true,
        account: {
            access_token: '',
            refresh_token: ''
        }}
    : { isLoggedIn: false,
        account: null };

export default function (state = initialState, action) {
    const { type, payload } = action;
    switch (type) {
        case REGISTER_SUCCESS:
            return {
                ...state,
                isLoggedIn: false,
            };
        case REGISTER_FAIL:
            return {
                ...state,
                isLoggedIn: false,
            };
        case LOGIN_SUCCESS:
            return {
                ...state,
                isLoggedIn: true,
                account: payload.user,
            };
        case LOGIN_FAIL:
            return {
                ...state,
                isLoggedIn: false,
                account: null,
            };
        case LOGOUT:
            return {
                ...state,
                isLoggedIn: false,
                account: null,
            };
        default:
            return state;
    }
}