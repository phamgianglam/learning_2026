import type { ItemDetail } from "../types/Item";
import config from "../config";

const base_url = config.apiUrl;
const item_url = `${base_url}/items`;

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