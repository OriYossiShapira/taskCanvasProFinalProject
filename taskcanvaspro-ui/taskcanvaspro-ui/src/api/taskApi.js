import { TOKEN } from "../AppContext";
const url = "http://localhost:8080/api/v1/tasks"

const getToken = () => {
    const strToken = localStorage.getItem(TOKEN);
    if(strToken){
        return JSON.parse(strToken)
    }
    return undefined
   
}
export const getTask = async (taskId) => {
    
    const result = {ok:true, data:null, message:""}
    const token = getToken();
    try{
        const response = await fetch(url + "/" + taskId, {
            method:'GET',
            headers:{Authorization : `Bearer ${token}`}
        });
        if(response.status >= 400 && response.status < 500){
            result.ok = false;
            result.message = "invalid task";
            return result;
        }else if(response.status >= 500){
            throw new Error();
        }
        const task = await response.json()
        result.data = task;
        return result;
    }catch(e){
        console.log(e, e.message);
        result.ok = false;
        result.message = "server error, please try again later";
        return result;
    }
    
}

export const getTaskList = async () => {
    const strUser = localStorage.getItem("user");
    let user = {}
    if(strUser){
        user = JSON.parse(strUser)
    }
    const token = getToken();
    const result = {ok:true, data:null, message:""}
    try{
        const response = await fetch(`${url}/user/${user.id}`, {
            method:'GET',
            headers:{Authorization : `Bearer ${token}`}
        });
    
        if(response.status >= 400 && response.status < 500){
            result.ok = false;
            result.message = "invalid task";
            return result;
        }else if(response.status >= 500){
            throw new Error();
        }
        const task = await response.json()
        result.data = task;
        return result;
    }catch(e){
        console.log(e, e.message);
        result.ok = false;
        result.message = "server error, please try again later";
        return result;
    }
    
}

export const postTask = async ( task) => {
    const token = getToken();
    const result = {ok:true, data:null, message:""}
    try{
        const response = await fetch(url, {
            method:'POST',
            headers:{'Content-Type':'application/json', Authorization : `Bearer ${token}`},
            body: JSON.stringify(task)
        });
        console.log(response);
        if(response.status >= 400 && response.status < 500){
            result.ok = false;
            result.message = "session expired, please login";
            return result;
        }else if(response.status >= 500){
            throw new Error();
        }
        const newTask = await response.json()
        console.log(newTask);
        result.data = newTask;
        return result;
    }catch(e){
        console.log(e, e.message);
        result.ok = false;
        result.message = "server error, please try again later";
        return result;
    }
    
}
export const putTask = async ( task) => {
    const token = getToken();
    const result = {ok:true, data:null, message:""}
    try{
        const response = await fetch(url, {
            method:'PUT',
            headers:{'Content-Type':'application/json', Authorization : `Bearer ${token}`},
            body: JSON.stringify(task)
        });
        console.log(response);
        if(response.status >= 400 && response.status < 500){
            result.ok = false;
            result.message = "Wrong email or password";
            return result;
        }else if(response.status >= 500){
            throw new Error();
        }
        const newTask = await response.json()
        console.log(newTask);
        result.data = newTask;
        return result;
    }catch(e){
        console.log(e, e.message);
        result.ok = false;
        result.message = "server error, please try again later";
        return result;
    }
    
}
