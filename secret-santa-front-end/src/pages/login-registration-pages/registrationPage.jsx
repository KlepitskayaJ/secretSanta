import React, {useState} from 'react';

import {Formik, Field, useFormikContext} from 'formik';
import * as Yup from 'yup';
import {Button, ButtonGroup, Col, Form, Container, Row} from 'react-bootstrap';

import './loginRegistration.css';
import {Link, NavLink, useNavigate} from "react-router-dom";
import {useDispatch} from "react-redux";
import PhoneInputField from "./additionalFields/phoneInputField";
import AddressInputField from "./additionalFields/addressInputField";
import {isValidPhoneNumber} from "react-phone-number-input";
import * as authActions from "../../redux/actions/authActions";

const RegistrationPage = () => {
    const dispatch = useDispatch();
    const navigate = useNavigate();
    const [phone, setPhone] = useState("");
    // const [address, setAddress] = useState('');
    const [submitClicked, setSubmitClicked] = useState(false);
    const [stateOfAlert, setStateOfAlert] = useState(false);

    const changeSubmit = () => {
        setSubmitClicked(!submitClicked);
    }

    const schema = Yup.object().shape({
        email: Yup.string()
            .email('Invalid email')
            .required('Email is required field'),
        password: Yup.string()
            .required('Password is required field')
            .matches(
                /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{8,}$/,
                "Must minimum 8 characters: at least one uppercase letter, one lowercase letter and one number"
            ),
        firstName: Yup.string()
            .min(2, 'First name should contain minimum 2 characters.')
            .max(30, 'First name should contain maximum 30 characters.')
            .required('First name is required field'),
        address: Yup.string()
            .min(2, 'Address should contain minimum 5 characters.')
            .required('Address is required field'),
        postcode: Yup.string()
            .min(2, 'Postcode should contain minimum 2 characters.')
            .max(10, 'Postcode should contain maximum 10 characters.')
            .required('Last name is required field')
    });

    return (
        <Formik
            initialValues={{
                email: '',
                password: '',
                firstName: '',
                lastName: '',
                address: '',
                postcode: ''
            }}
            validationSchema={schema}
            validateOnChange={false}
            validateOnBlur={false}
            onSubmit={(values) => {
                // if (!phone || (phone && isValidPhoneNumber(phone))) {
                //     authActions.register(values.email, values.password, values.firstName, values.lastName, phone, values.address, values.postcode)(dispatch).then(() => {
                //         navigate('/');
                //     }).catch(() => {
                //         setStateOfAlert(true);
                //         setTimeout(() => {
                //             setStateOfAlert(false);
                //         }, 3000)
                //     });
                // }
                authActions.register(values.email, values.password, values.firstName, values.lastName, phone, values.address, values.postcode)(dispatch).then(() => {
                    navigate('/');
                });
            }}
        >
            {({
                  handleSubmit,
                  handleChange,
                  errors,
              }) => (
                <Container id="main-container">
                    {/*{stateOfAlert ? <div className="alert alert-danger" role='alert'>Error on the server</div> : null}*/}
                    <Col lg={6} md={6} sm={12} className="m-auto shadow-sm full-width d-flex justify-content-center">
                        <Form id="sign-up-form" className="m-5 p-4 rounded w-75" noValidate onSubmit={handleSubmit}>
                            <div className="text-center">
                                <div className="header">Registration</div>
                                <h6 className="mb-0">Already have an account?</h6>
                                <NavLink className="text-decoration-none" to="/login">
                                    <h6 className="mt-0 mb-0 text-danger little-link">Log in</h6>
                                </NavLink >
                            </div>
                            <h2 className="text-start text-danger fs-3 ml-2 p-3 pt-4 pb-1">Account info</h2>
                            <Form.Group className="p-4 pt-0" controlId="sign-in-email-address">
                                <Form.Label>Email</Form.Label>
                                <Form.Control type="email" name="email" placeholder="Enter email" onChange={handleChange} isInvalid={!!errors.email}/>
                                <Form.Control.Feedback type="invalid">
                                    {errors.email}
                                </Form.Control.Feedback>
                            </Form.Group>
                            <Form.Group className="p-4 pt-0" controlId="sign-in-password">
                                <Form.Label>Password</Form.Label>
                                <Form.Control type="password" name="password" placeholder="Password" onChange={handleChange} isInvalid={!!errors.password}/>
                                <Form.Control.Feedback type="invalid">
                                    {errors.password}
                                </Form.Control.Feedback>
                            </Form.Group>
                            <h2 className="text-start text-danger fs-3 ml-2 p-3 pb-1">Personal info</h2>
                            <Form.Group className="p-4 pt-0" controlId="sign-up-first-name">
                                <Form.Label>First name</Form.Label>
                                <Form.Control type="textarea" name="firstName" placeholder="Enter first name" onChange={handleChange} isInvalid={!!errors.firstName}/>
                                <Form.Control.Feedback type="invalid">
                                    {errors.firstName}
                                </Form.Control.Feedback>
                            </Form.Group>
                            <Form.Group className="p-4 pt-0" controlId="sign-up-last-name">
                                <Form.Label>Last name</Form.Label>
                                <Form.Control type="textarea" name="lastName" placeholder="Enter last name" onChange={handleChange} isInvalid={!!errors.lastName}/>
                                <Form.Control.Feedback type="invalid">
                                    {errors.lastName}
                                </Form.Control.Feedback>
                            </Form.Group>
                            <PhoneInputField phone={phone} changePhone={setPhone} submitClicked={submitClicked} required={false}/>
                            {/*<AddressInputField address={address} changeAddress={setAddress} submitClicked={submitClicked}/>*/}
                            {/*/!*TODO: add react address (google maps) validator*!/*/}
                            <Form.Group className="p-4 pt-0" controlId="sign-up-address">
                                <Form.Label>Address</Form.Label>
                                <Form.Control type="textarea" name="address" placeholder="Enter address" onChange={handleChange} isInvalid={!!errors.address}/>
                                <Form.Control.Feedback type="invalid">
                                    {errors.address}
                                </Form.Control.Feedback>
                            </Form.Group>
                            {/*/!*TODO: add react postcode validator*!/*/}
                            <Form.Group className="p-4 pt-0" controlId="sign-up-postcode">
                                <Form.Label>Postcode</Form.Label>
                                <Form.Control type="textarea" name="postcode" placeholder="Enter postcode" onChange={handleChange} isInvalid={!!errors.postcode}/>
                                <Form.Control.Feedback type="invalid">
                                    {errors.postcode}
                                </Form.Control.Feedback>
                            </Form.Group>
                            <div className="mt-3 text-center ">
                                <Button type="submit" variant="danger" size="lg" onClick={changeSubmit}>Create account</Button>
                            </div>
                        </Form>
                    </Col>
                </Container>
            )}
        </Formik>
    );
};

export default RegistrationPage;