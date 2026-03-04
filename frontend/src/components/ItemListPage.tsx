import { Link } from "react-router"
import Item from "./Item";
import { itemClient } from "../api/item";
import { useQuery } from "@tanstack/react-query";

export default function ItemListPage() {
    const {data: items, isLoading, isError, error} = useQuery({
        queryKey: ["items"],
        queryFn: () => itemClient.getItemList(),
    })

    if (isLoading) return <div className="p-10 text-center">Loading item details...</div>;
  
    if (isError) return (
        <div className="p-10 text-red-500">
        Error loading item: {error instanceof Error ? error.message : 'Unknown error'}
        </div>
    );
    
    if (!items || !Array.isArray(items) || items.length === 0) {
        return <div className="p-10 text-center text-gray-400">Your store is empty.</div>;
    }


    return (
        <div className="grid grid-cols-4 gap-4">
            {items?.map(item => (
                <Link key={item.id} to={`/item/${item.id}`}>
                    <div>
                        <Item item={item} />
                    </div>
                </Link>
            ))}
        </div>
    )
}
