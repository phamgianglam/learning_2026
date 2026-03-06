interface AppConfig {
    apiUrl: String
}

const config: AppConfig = {
    apiUrl: String = import.meta.env.VITE_BACKEND_SERVICE_URL
}

export default Object.freeze(config);