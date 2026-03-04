import type { ItemDetail } from "../types/Item";

const base_url = import.meta.env.VITE_BACKEND_SERVICE_URL
const item_url = `${base_url}/item`

class ItemClient {
    async getItem(id: string): Promise<ItemDetail>{
        const respone = await fetch(`${item_url}/${id}`)
        if (!respone.ok) {
            throw new Error('Encounter error when fetching data')
        }

        return respone.json()
    }

    async getItemList(): Promise<ItemDetail[]> {
        const respone = await fetch(item_url);
        if (!respone.ok){
            throw new Error('Encounter error when fetching data')
        }

        return respone.json();
    }
}

export const itemClient = new ItemClient();