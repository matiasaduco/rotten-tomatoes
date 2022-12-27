import ButtonPage from './ButtonPage'

const Pagination = ({activePage, setActivePage, list, componentPerPage}) => {
    const buttons = [...Array(Math.ceil(list.length / componentPerPage)).keys()];

    return(
        <nav>
            <ul className="pagination">
                <li className={`page-item ${activePage === 1 ? 'disabled' : ''}`}>
                    <button className="page-link" onClick={() => setActivePage(activePage - 1)}>Previous</button>
                </li>
                {
                    buttons.map(button => <ButtonPage key={`ButtonKey_${button}`} quotient={Math.ceil((button+componentPerPage/componentPerPage))} activePage={activePage} setActivePage={setActivePage}/>)
                }
                <li className={`page-item ${activePage === buttons.length ? 'disabled' : ''}`}>
                    <button className="page-link" onClick={() => setActivePage(activePage + 1)}>Next</button>
                </li>
            </ul>
        </nav>
    )
}

export default Pagination
