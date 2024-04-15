import { FileType } from '@prisma/client';

export default interface AttachmentDto {
  id: number;
  fileName: string;
  fileType: FileType;
}
