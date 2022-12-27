import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom'
import Api from '../main/mainPage-components/Api'

const CategoriesDropdown = () => {
    const navigate = useNavigate()
    const [categories, setCategories] = useState([])
    
    useEffect(() => { Api.getCategories().then((data) => setCategories(data.result)) },[]);
    
    return(
        <li className="nav-item dropdown ms-2 me-5">
            <a className="nav-link dropdown-toggle" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false"><strong>Categories</strong></a>
            <ul className="dropdown-menu" aria-labelledby="navbarDropdown">
                {categories.map(category => 
                    <li key={`DropDown_btn_${category.id}`}>
                        <button className="dropdown-item" onClick={() => navigate(`/categories/${category.id}`)}>{category.name}</button>
                    </li>
                )}
            </ul>
        </li>    
    )
}

export default CategoriesDropdown;
