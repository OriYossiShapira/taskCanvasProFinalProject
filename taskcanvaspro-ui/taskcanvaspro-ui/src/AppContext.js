import React, { useState, useEffect } from 'react'

export const USER = "user";
export const TOKEN = "token";
const AppContext = React.createContext({
    flagUpdate:() => undefined,
    unflagUpdate:() => undefined,
    doUpdate: false, 
    setDoUpdate:(flag) => undefined,
    onLogin:(user, token) => {},
    getToken:() => undefined,
    onLogout:() => {},
    getUser:() => undefined,
})

export const ContextProvider = ({children}) => {
    const [token, setToken] = useState();
    const [user, setUser] = useState();
    const [doUpdate, setDoUpdate] = useState(false);

    useEffect( () => {
        const rawUser = localStorage.getItem(USER);
        if(rawUser){
            const user = JSON.parse(rawUser);
            setUser(user);
        }
        const rawToken = localStorage.getItem(TOKEN);
        if(rawToken){
            const token = JSON.parse(rawToken);
            setUser(token);
        }
    }, [])

    const flagUpdate = () => setDoUpdate(true);
    const unflagUpdate = () => setDoUpdate(false);
    const isLoggedin = () => user !== undefined;
    
    const onLogin = (user, token) => {
        setUser(user);
        setToken(token)
        localStorage.setItem(USER, JSON.stringify(user));
        localStorage.setItem(TOKEN, JSON.stringify(token));
    }

    const onLogout = () => {
        setUser(undefined);
        setUser(setToken);
        localStorage.removeItem(USER);
        localStorage.removeItem(TOKEN);
    }

    const getUser = () => user;
    const getToken = () => token;

    return(
        <AppContext.Provider value={{isLoggedin,doUpdate, flagUpdate, unflagUpdate, getUser, getToken, onLogin, onLogout}}>
            {children}
        </AppContext.Provider>
    )
}

export default AppContext;