import MenuItemDto from './menu-item.dto';

export default interface OrderItemDto {
  id: number;
  menuItem: MenuItemDto;
  quantity: number;
}
