const TabContent = ({name, content, getActiveTab}) => {
    return(
        <div key={`DivTabContentKey_${name}`} className={`modal-body p-3 ${getActiveTab === name ? 'd-block' : 'd-none'}`} id={`TabContent_${name}`}>{content}</div>
    )
}

export default TabContent
