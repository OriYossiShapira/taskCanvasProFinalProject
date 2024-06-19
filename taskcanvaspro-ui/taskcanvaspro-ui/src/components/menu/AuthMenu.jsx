import {Link} from 'react-router-dom';
import './TaskMenu.css'

const AuthMenu = () => {

    return(
      <div className="menu">
        <Link to="/">HOME</Link>
        <Link to="/login">LOGIN</Link>
        <Link to="/register">REGISTER</Link>
      </div>
    )
}

export default AuthMenu;