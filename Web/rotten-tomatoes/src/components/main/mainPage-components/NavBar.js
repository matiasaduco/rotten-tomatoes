import CategoriesDropdown from "../../sub-components/CategoriesDropdown";
import { Link, Outlet } from 'react-router-dom'
import SessionManager from "../../session-components/SessionManager";
import { Fragment } from "react";

const NavBar = ({setAnActiveSession}) => {

    return(
        <Fragment>
            <nav className="navbar navbar-expand-lg bg-light w-75 mx-auto border-bottom border-dark border-1">
                <div className="container-fluid">
                    <div className="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul className="navbar-nav me-auto mb-2 mb-lg-0">
                            <li className="nav-item ms-2">
                                <Link className="nav-link" aria-current="page" to="/"><strong>Home</strong></Link>
                            </li>
                            <li className="nav-item ms-2">
                                <Link className="nav-link" to="content/top_movies"><strong>Top Movies</strong></Link>
                            </li>
                            <li className="nav-item ms-2">
                                <Link className="nav-link" to="content/latest_movies"><strong>Latest Movies</strong></Link>
                            </li>
                            <CategoriesDropdown />
                        </ul>
                    </div>
                    <SessionManager setAnActiveSession={setAnActiveSession}/>
                </div>
            </nav>
            <Outlet/>
        </Fragment>
  );
}

export default NavBar;
