import type { ItemDetail } from "../types/ItemDetail";
import Item from "./Item";

export default function ItemListPage({ items }: { items: ItemDetail[] }) {
    return (
        <div className="grid grid-cols-4 gap-4">
            {items.map(item => (
                <div className="aspect-auto">
                    <Item key={item.id} item={item} />
                </div>
            ))}
        </div>
    )
}