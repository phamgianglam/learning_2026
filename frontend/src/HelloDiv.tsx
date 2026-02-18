export default function HelloDiv ({isLogin}: {isLogin?: boolean}) {
    if (isLogin) {
        return <div> Hello, Buddy</div>
    } else {
        return <div>You are not login</div>
    }
}