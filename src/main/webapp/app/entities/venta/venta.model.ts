import dayjs from 'dayjs/esm';
import { ICoche } from 'app/entities/coche/coche.model';
import { IEmpleado } from 'app/entities/empleado/empleado.model';
import { ICliente } from 'app/entities/cliente/cliente.model';

export interface IVenta {
  id?: number;
  fecha?: dayjs.Dayjs | null;
  tipoPago?: string | null;
  total?: number | null;
  ventas?: ICoche[] | null;
  empleado?: IEmpleado | null;
  cliente?: ICliente | null;
  coche?: ICoche | null;
}

export class Venta implements IVenta {
  constructor(
    public id?: number,
    public fecha?: dayjs.Dayjs | null,
    public tipoPago?: string | null,
    public total?: number | null,
    public ventas?: ICoche[] | null,
    public empleado?: IEmpleado | null,
    public cliente?: ICliente | null,
    public coche?: ICoche | null
  ) {}
}

export function getVentaIdentifier(venta: IVenta): number | undefined {
  return venta.id;
}
