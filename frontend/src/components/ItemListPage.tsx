import { Link } from "react-router"
import type { ItemDetail } from "../types/ItemDetail";
import Item from "./Item";

export default function ItemListPage({ items }: { items: ItemDetail[] }) {
    return (
        <div className="grid grid-cols-4 gap-4">
            {items.map(item => (
                <Link key={item.id} to={`/item/${item.id}`}>
                    <div>
                        <Item key={item.id} item={item} />
                    </div>
                </Link>
            ))}
        </div>
    )
}
