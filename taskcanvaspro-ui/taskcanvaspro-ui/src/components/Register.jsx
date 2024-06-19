import {useRef, useState} from 'react';
import { register } from '../api/authApi';
import { useNavigate } from 'react-router-dom';

const Register = () => {
    const firstName = useRef(null);
    const lastName = useRef(null);
    const email = useRef(null);
    const password = useRef(null);
    
    const [error, setError] = useState(null);

    const nav = useNavigate()

    const onSubmit =async (event) => {
        event.preventDefault(); 
        const user = {};
        user.firstName = firstName.current.value;
        user.lastName = lastName.current.value;
        user.email = email.current.value;
        user.password = password.current.value;

        const result = await register(user);
        console.log('done', result);
        if(result.ok){
            setError(null);
            nav("/login")
        }else{
            setError(result.message)
        }
    }
    return(
        <form onSubmit={onSubmit}>
            <h2>register</h2>
            {error !== null && <div>{error}</div>}
            <label htmlFor="firstName">First Name</label>
            <input ref={firstName} type="text" name="firstName" id="firstName" required/>
            <label htmlFor="lastName">Last Name</label>
            <input ref={lastName} type="text" name="lastName" id="lastName" required/>
            <label htmlFor="email">Email</label>
            <input ref={email} type="email" name="email" id="email" required/>
            <label htmlFor="password">Password</label>
            <input ref={password} type="password" name="password" id="password" required />
            <button type="submit">Register</button>
        </form>
    )
}

export default Register;