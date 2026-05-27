document.addEventListener('DOMContentLoaded', () => {
    // добавляем класс для скролл-анимаций
    const observer = new IntersectionObserver((entries) => {
        entries.forEach(entry => {
            if(entry.isIntersecting) {
                entry.target.classList.add('animate-scale');
                observer.unobserve(entry.target);
            }
        });
    }, { threshold: 0.1 });
    document.querySelectorAll('.glass-card, .stat-card').forEach(el => observer.observe(el));
});