
export default function Navbar () {
    const navStyle = "nav h-15 bg-gray-400 fixed flex top-0 left-0 right-0 w-full items-center";
    const buttonStyle = "";
    const logoLocation: string = "../asset/logo.png" 
    return (
    <div className="nav h-15 bg-gray-400 fixed flex top-0 left-0 right-0 w-full items-center">
        <a href="loginpage"><img src={logoLocation} alt="logo" className="size-10 m-0.5"/></a>
        <button className="p-0.5 bg-gray-500 border-x-2 text-white ">Home</button>
        <input className="ml-auto mr-20 w-[15%] bg-amber-50" type="text" placeholder="search"/>
    </div>
    )
}
