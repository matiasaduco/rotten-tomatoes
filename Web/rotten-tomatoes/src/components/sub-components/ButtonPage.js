const ButtonPage = ({quotient, activePage, setActivePage}) => {
    const checkActiveTab = (id) => {
        const result = id === activePage ? 'active' : ''
        return result
    }

    return(
        <li className={`page-item ${checkActiveTab(quotient)}`}>
            <button className="page-link" onClick={() => setActivePage(quotient)}>{quotient}</button>
        </li>
    )
}

export default ButtonPage
