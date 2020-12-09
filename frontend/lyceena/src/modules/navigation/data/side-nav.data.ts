import { SideNavItems, SideNavSection } from '@modules/navigation/models';

export const sideNavSections: SideNavSection[] = [
    {
        text: 'ADMINISTRATION',
        items: ['students','teachers'],
    },
    {
        text: 'RÉFÉRENTIELS',
        items: ['materials','classes'],
    },
];

export const sideNavItems: SideNavItems = {
    students: {
        icon: 'tachometer-alt',
        text: 'Élèves',
        link: '/students',
    },

    teachers: {
        icon: 'tachometer-alt',
        text: 'Enseignant',
        link: '/teachers',
    },

    materials: {
        icon: 'tachometer-alt',
        text: 'Matières',
        link: '/materials',
    },

    classes: {
        icon: 'tachometer-alt',
        text: 'Classes',
        link: '/classes',
    },
};
