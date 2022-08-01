import React, {useState} from 'react';
import {Button, ButtonGroup, Col, Container, Form, Row} from 'react-bootstrap';
import { Formik, Field } from 'formik';
import * as Yup from 'yup';

import './loginRegistration.css';
import {Link,  useNavigate } from "react-router-dom";
import {useDispatch} from "react-redux";
import * as authActions from "../../redux/actions/authActions";
// import * as authActions from "../../redux/actions/authActions";


const LoginPage = () => {
    const navigate = useNavigate();
    const dispatch = useDispatch()
    const [stateOfAlert, setStateOfAlert] = useState(false);

    const schema = Yup.object().shape({
        email: Yup.string()
            .email('Invalid email')
            .required('Email is required field'),
        password: Yup.string()
            .required('Password is required field'),
    });

    return (
        <Formik
            initialValues={{
                email: '',
                password: '',
            }}
            validationSchema={schema}
            validateOnChange={false}
            validateOnBlur={false}
            onSubmit={(values) => {
                console.log(values.email, values.password);
                authActions.login(values.email, values.password)(dispatch).then(() => {
                    // navigate('/');
                }).catch((error) => {
                    setStateOfAlert(true);
                    setTimeout(() => {
                        setStateOfAlert(false);
                    }, 3000)
                });
            }}
        >
            {({
                  handleSubmit,
                  handleChange,
                  errors, }) => (
                <Container id="main-container">
                    {/*{stateOfAlert ? <div className="alert alert-danger" role='alert'>Invalid login or email</div> : null}*/}
                    <Col md={5} sm={12} className="m-auto shadow-sm full-width d-flex justify-content-center">
                        <Form id="sign-in-form" className="m-5 pt-4 pb-3 p-2 rounded w-75" noValidate onSubmit={handleSubmit}>
                            <div className="text-center">
                                <h1 className="header">Login</h1>
                                <h6 className="mb-0">Don't have an account?</h6>
                                <Link className="text-decoration-none" to="/register">
                                    <h6 className="mt-0 text-danger">Register</h6>
                                </Link>
                            </div>
                            <Form.Group className="p-4 mt-2" controlId="sign-in-email-address">
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
                            <div className="mt-3 text-center ">
                                <Button type="submit" variant="danger" size="lg">Sign in</Button>
                            </div>
                        </Form>
                    </Col>
                </Container>
            )}
        </Formik>
    );
};

export default LoginPage;