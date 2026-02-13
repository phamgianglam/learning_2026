const MyButton = ({}) => {
    const handleClick = (event: React.MouseEvent) => {
        alert("Button pressed! " + event.target);
    }

    return <button onClick={handleClick}>Press me</button>
}

export default MyButton;