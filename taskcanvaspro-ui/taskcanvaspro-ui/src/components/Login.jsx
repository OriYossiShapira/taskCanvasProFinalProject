import { useRef, useState, useContext } from 'react';
import { login } from "../api/authApi";
import AppContext from '../AppContext';
import { useNavigate } from 'react-router-dom';
import './Login.css'

const Login = () => {
    const email = useRef(null);
    const password = useRef(null);
    const [error, setError] = useState(null);
    const nav = useNavigate();
    const context = useContext(AppContext)


    const onSubmit = async (event) => {
        event.preventDefault();
        const result = await login(email.current.value, password.current.value);
        console.log('done', result);
        if (result.ok) {
            context.onLogin(result.data.user, result.data.token);
            setError(null);
            nav("/tasks")
        } else {
            setError(result.message)
        }
    }
    return (
        <div className='login'>
            <h2>LOGIN</h2>
            {error !== null && <div>{error}</div>}
            <form onSubmit={onSubmit}>
                <div>
                    <label htmlFor="email">Email</label>
                    <input ref={email} type="email" name="email" id="email" defaultValue="aaa@mail.com" required />
                </div>
                <div>
                    <label htmlFor="password">Password</label>
                    <input ref={password} type="password" name="password" id="password" defaultValue="1q2w3e" required />
                </div>
                <div>
                    <button type="submit">Login</button>
                </div>
            </form>
        </div>

    )
}

export default Login;