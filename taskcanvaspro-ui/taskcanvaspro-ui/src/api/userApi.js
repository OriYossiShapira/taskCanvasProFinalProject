const url = "http://localhost:8080/api/v1/users"
export const getTasks = async () => {
    
    const result = {ok:true, data:null, message:""}
    try{
        const response = await fetch(url + "/login", {
            method:'POST',
            headers:{'Content-Type':'application/json'},
            body: JSON.stringify({email, password})
        });
        console.log(response);
        if(response.status >= 400 && response.status < 500){
            result.ok = false;
            result.message = "Wrong email or password";
            return result;
        }else if(response.status >= 500){
            throw new Error();
        }
        const user = await response.json()
        console.log(user);
        result.data = user;
        return result;
    }catch(e){
        console.log(e, e.message);
        result.ok = false;
        result.message = "server error, please try again later";
        return result;
    }
    
}
