import React, {useEffect, useState} from "react";
import PlacesAutocomplete from "react-places-autocomplete";
import {Form} from "react-bootstrap";

const AddressInputField = ({ address, changeAddress, submitClicked }) => {
    const [inputAddress, setInputAddress] = useState("");
    const [error, setError] = useState("");

    useEffect(() => {
        if (submitClicked) {
            validateAddress(inputAddress);
        }
    }, [submitClicked]);

    function validateAddress(input) {
        if (address === "" || (address !== "" && input !== "")) {
            setInputAddress(input);
            changeAddress(input);
        }
    }

    return (
        <PlacesAutocomplete
            value={address ? address : inputAddress}
            onChange={validateAddress}
            onSelect={validateAddress}
        >
            {({ getInputProps, suggestions, getSuggestionItemProps }) => (
                <Form.Group className="p-4 pt-0" controlId="address-input">
                    <Form.Label>Address</Form.Label>
                    <input {...getInputProps({ placeholder: "Enter address", className: "form-control"} )} />

                    <div className="form-control-feedback" style={{display: error !== '' ? 'block' : 'none' }}>
                        { error === 'ZERO_RESULTS' ? 'There is no such address. Please select it from the suggestions.' : 'Address error'}
                    </div>
                    <div className="address-suggestions">
                        {suggestions.map(suggestion => {
                            const style = {
                                backgroundColor: suggestion.active ? "#c8c8c8" : "#fff"
                            };
                            return (
                                <div {...getSuggestionItemProps(suggestion, { style })}>
                                    {suggestion.description}
                                </div>
                            );
                        })}
                    </div>
                </Form.Group>
            )}
        </PlacesAutocomplete>
    );
}

export default AddressInputField;