const GenericTab = ({name, icon, getActiveNavTab, setActiveNavTab}) => {
    return ( 
        <li className="nav-item" key={`LiTabDataKey_${name}`}>
            <button className={`nav-link ${getActiveNavTab === name ? 'active font-weight-bold' : ''} text-secondary`} data-tab-target={`#${name}`} onClick={() => setActiveNavTab(name)}>
                <img src={icon} alt="Tab icon" width='30' height='24' />
                {name}
            </button>
        </li>
    )
}

export default GenericTab
