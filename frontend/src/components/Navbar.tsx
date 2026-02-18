
export default function Navbar () {
    const navStyle: string = "flex h-20 w-full top-0 nav bg-gray-400 items-center";
    const mainNavContent: string = "flex h-[80%] w-250 mx-auto left-0 right-0 items-center justify-evenly"
    const logoStyle = "h-[75%] aspect-square";
    const buttonStyle = "h-[75%] hover:bg-gray-500 rounded-xl ml-2"
    const inputSyle = "bg-white h-[75%] ml-auto mr-20 w-[50%]" 
    const logoLocation: string = "../asset/logo.png" 
    return (
    <div className={navStyle}>
        <div className={mainNavContent}>
                <a href="loginpage"><img src={logoLocation} alt="logo" className={logoStyle}/></a>
                <button className={buttonStyle}>Home</button>
                <input className={inputSyle} type="text" placeholder="search"/>
                {/* <a href="" */}
        </div>
    </div>
    )
}
